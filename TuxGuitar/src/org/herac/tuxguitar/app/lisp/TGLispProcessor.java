package org.herac.tuxguitar.app.lisp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import kevwargo.jlp.LispProcessor;
import kevwargo.jlp.objects.LispJavaObject;
import kevwargo.jlp.parser.LispParser;

import org.herac.tuxguitar.app.TuxGuitar;
import org.herac.tuxguitar.app.lisp.actions.impl.RunAction;
import org.herac.tuxguitar.app.lisp.actions.impl.SetFretAction;
import org.herac.tuxguitar.app.lisp.data.LispContext;
import org.herac.tuxguitar.app.lisp.functions.impl.CurrentSong;
import org.herac.tuxguitar.app.lisp.functions.impl.GetCaret;
import org.herac.tuxguitar.app.lisp.functions.impl.DefaultActionContext;
import org.herac.tuxguitar.app.lisp.functions.impl.DefineKeyBinding;
import org.herac.tuxguitar.app.lisp.macros.DefineAction;
import org.herac.tuxguitar.app.util.TGFileUtils;
import org.herac.tuxguitar.util.TGContext;


public class TGLispProcessor {

    private static TGLispProcessor instance;
    private TGContext context;
    private LispProcessor processor;


    private TGLispProcessor(TGContext context) {
        this.context = context;
        processor = LispProcessor.getInstance();
        init();
    }

    public static TGLispProcessor getInstance(TGContext context) {
        synchronized (TGLispProcessor.class) {
            if (instance == null) {
                instance = new TGLispProcessor(context);
            }
            return instance;
        }
    }

    public void start(InetAddress socketHost) {
        try {
            int port = 39012;
            final ServerSocket serverSocket = new ServerSocket(port, 10, socketHost);
            System.out.printf("Starting jlp server socket at %s:%d\n", socketHost, port);

            new Thread() {
                public void run() {
                    try {
                        while (!serverSocket.isClosed()) {
                            Socket clientSocket = serverSocket.accept();
                            System.err.printf("JLP client %s connected\n", clientSocket);
                            InputStream in = clientSocket.getInputStream();
                            PrintStream out = new PrintStream(clientSocket.getOutputStream());
                            LispParser parser = new LispParser(in);
                            while (true) {
                                try {
                                    TGLispProcessor.this.processor.process(parser, out);
                                    break;
                                } catch (Exception e) {
                                    e.printStackTrace(out);
                                }
                            }
                            clientSocket.close();
                            System.err.printf("JLP client %s disconnected\n", clientSocket);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void init() {
        processor.define(new DefineAction(context));
        processor.define(new RunAction(context));
        processor.define(new DefaultActionContext(context));
        processor.define(new DefineKeyBinding(context));
        processor.define(new CurrentSong(context));
        processor.define(new GetCaret(context));

        processor.define(new SetFretAction(context).register());

        processor.define("*tuxguitar*", new LispJavaObject(TuxGuitar.getInstance()));
        processor.define("*context*", new LispContext(context));

        loadInitFile();
    }

    private void loadInitFile() {
        String fileName = TGFileUtils.PATH_USER_CONFIG + File.separator + "init.lisp";
        try {
            processor.process(new LispParser(fileName));
        } catch (Exception e) {}
    }

}
