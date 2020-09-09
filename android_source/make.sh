#!/bin/sh

export JAVA_HOME=/home/sl.truman/Public/jdk1.6.0_45
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar


make lxoxfcommon
make -j4
