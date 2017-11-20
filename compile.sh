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

# if directory $out doesn't exist, create it
mkdir -p $out


# no options passed
if [[ $# -eq 0 ]]; then
	#TODO >1 .compile.cfg handling to customize compilation process (adding custom libraries etc.)
	echo "Compiling whole project. If you want to compile a subdirectory, pass it as an argument."
	javac -d $out -sourcepath src $(find $src -name "*.java")
	if [[ $? -eq 0 ]]; then exit 1; fi
# option to compile submodule passed
elif [[ $1 == "-p" ]]; then
	module="$2"
	if [[ -d $src/$module ]]; then
        dir="$src/$2"
        if [[ -d $dir ]]; then
            echo "Compiling $dir with default settings."
            javac -d $out -sourcepath $dir $(find $dir -name "*.java")
            if [[ $? -eq 0 ]]; then exit 1; fi
        else
            echo "Directory $dir not found. Pass name of directory or leave empty to compile whole project."
            exit 1;
        fi
	fi
else
    echo "Wrong option. Use ./compile.sh [-p <moduleName>]"
    exit 1;
fi
