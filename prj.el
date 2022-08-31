(defun tg-dirs (basedir suffix)
  (remove-if-not 'file-directory-p
                 (mapcar (lambda (d)
                           (concat d suffix))
                         (directory-files basedir
                                          t "^TuxGuitar.*"))))

(setq jdee-sourcepath
      (append (tg-dirs (file-name-directory load-file-name) "/src")
              '("/mnt/data/coding/jlp/src")))
(setq jdee-global-classpath
      (append (tg-dirs (file-name-directory load-file-name) "/target/classes")
              '("/mnt/data/coding/jlp/target/classes")))

