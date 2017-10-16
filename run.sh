#!/bin/bash
. project.cfg
if [[ $1 != "-p" ]]; then
	echo "Executing newest version of the project."
	echo "If you want a specific one, use ./run.sh -p <name> <arguments...>."
	#TODO >3 look >2 .run.cfg handling to customize compilation process (adding custom libraries etc.)
	java -cp out "$newestMainClass" ${@:1}
elif [[ $1 == "-p" ]]; then
	dir="$2"
	if [[ -d out/$dir ]]; then
		#TODO >4 look >3
		#if [ -e "$dir/.compile.cfg" ]; then
			#echo "Compiling $dir with custom compile.sh"
			#javac -d out -sourcepath $dir $(find $dir -name "*.java")
		#else
            eval "main=$dir.\$$dir"
			echo "Executing $main with default settings."
			java -cp out "$main" ${@:3}
		#fi
	else
		echo "Directory $dir not found in out/ - maybe not compiled?"
	fi
else
    echo "Wrong command. Use ./run.sh <arguments...> to run the newest version of project."
fi
