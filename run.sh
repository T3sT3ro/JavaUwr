#!/bin/bash
. project.cfg

# check if src variable is set via parameter expansion
if [[ -z ${src+null} ]]; then
    echo "Variable 'src' in project.cfg not set!"
    exit 1
fi
# check if out variable is set via parameter expansion
if [[ -z ${out+null} ]]; then
    echo "Variable 'out' in project.cfg not set!"
    exit 1
fi

# if no option is specified
if [[ $# -eq 0 ]]; then
	echo "Executing newest version of the project."
	echo "If you want a specific one, use ./run.sh <name> <arguments...>."
	#TODO look >1
	# passess argument list to program starting from arg1
	java -cp $out "$newestMainClass" ${@:1}
	if [[ $? -eq 0 ]]; then exit 1; fi
# run certain project passed
elif [[ $1 == "-p" ]]; then
	module="$2"
	if [[ -d $out/$module ]]; then
        #substitutes with variable with name $module from project.cfg
        eval "main=$module.\$$module"
        echo "Executing $main with default settings."
        java -cp $out "$main" ${@:3}
        if [[ $? -eq 0 ]]; then exit 1; fi
	else
		echo "Directory $module not found in directory $out"
		./compile.sh $module
		exit 1;
	fi
else
    echo "Wrong command. Use ./run.sh [-p <moduleName>] [arguments...]"
    exit 1;
fi
