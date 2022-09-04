#!/bin/sh
##SCRIPT DIR
DIR_NAME=`dirname "$(realpath "$0")"`

if [ -z ${JAVA} ]; then
	[ -z ${JAVA_HOME} ] && JAVA_HOME="/usr"
	[ ! -f "${JAVA}" ] && JAVA="${JAVA_HOME}/bin/java"
	[ ! -f "${JAVA}" ] && JAVA="java"
fi
##LIBRARY_PATH
LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:"${DIR_NAME}/lib/"
LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:/lib
LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:/usr/lib
LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:/usr/lib/jni
LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:/usr/local/lib
##CLASSPATH
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-ui-toolkit.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-ui-toolkit-qt5.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-lib.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-editor-utils.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-gm-utils.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/tuxguitar-awt-graphics.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/qtjambi.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/qtjambi-native.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/gervill.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/itext-pdf.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/itext-xmlworker.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/commons-compress.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/icedtea-sound.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/lib/jlp.jar"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/share/"
CLASSPATH=${CLASSPATH}:"${DIR_NAME}/dist/"
##MAINCLASS
MAINCLASS=org.herac.tuxguitar.app.TGMainSingleton
##JVM ARGUMENTS
VM_ARGS="-Xmx512m"
##EXPORT VARS
export CLASSPATH
export LD_LIBRARY_PATH
##LAUNCH
${JAVA} ${VM_ARGS} -cp :${CLASSPATH} -Dtuxguitar.home.path="${DIR_NAME}" -Dtuxguitar.share.path="share/" -Djava.library.path="${LD_LIBRARY_PATH}" -Dorg.herac.tuxguitar.ui.qt.style=Fusion ${MAINCLASS} "$1" "$2"
