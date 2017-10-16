#!/bin/bash
mkdir -p out
if [ $# == 0 ]; then
	echo "Compiling whole project. If you want to compile a subdirectory, pass it as an argument."
	#TODO >1 .compile.cfg handling to customize compilation process (adding custom libraries etc.)
	javac -d out -sourcepath src $(find src -name "*.java")
else
	dir="src/$1"
	if [ -d $dir ]; then
		#TODO >2 look >1
		#if [ -e "$dir/.compile.cfg" ]; then
			#echo "Compiling $dir with custom compile.sh"
			#javac -d out -sourcepath $dir $(find $dir -name "*.java")
		#else
			echo "Compiling only $dir with default settings."
			javac -d out -sourcepath $dir $(find $dir -name "*.java")
		#fi
	else
		echo "Directory $dir not found."
	fi
fi
unset JavaUWrDIR