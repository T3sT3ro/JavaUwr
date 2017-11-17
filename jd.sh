#!/bin/bash
# javadoc generator
. project.cfg

# check if 'src' variable is set via parameter expansion
if [[ -z ${src+null} ]]; then
    echo "Variable 'src' in project.cfg not set!"
    exit 1
fi

# check if 'javadocPerModule' variable is set via
if [[ -z ${javadocPerModule+null} ]]; then
    echo "Variable 'javadocPerModule' in project.cfg not set!"
    exit 1
fi

if [[ $# -eq 0 ]]; then
	echo "Generating javadoc for whole project. If you want to generate for a subdirectory, pass it as an argument."
	#TODO >1
	if [[ $javadocPerModule == true ]]; then
        subdirectories=$(find $src -maxdepth 1 -name "javadoc" -prune -o -type d -print | sed "s/$src\///g; s/\//./g; /$src/d")
        for i in $subdirectories
        do
            files=$(find $src/$i -name "javadoc" -prune -o -type d -print | sed "s/$src\///g; s/\//./g; s/$src//g")
            javadoc -private -splitindex -use -author -version $files -sourcepath "./$src" -d "$src/$i/javadoc"
        done
	elif [[ $javadocPerModule == false ]]; then
        if [[ -z ${jdout+null} ]]; then
            echo "Variable 'jdout' in project.cfg not set!"
            exit 1
        fi
        files=$(find $src -name "javadoc" -prune -o -type d -print | sed "s/$src\///g; s/\//./g; s/$src//g")
        javadoc -private -splitindex -use -author -version $files -sourcepath "./$src" -d "$jdout"
        javac -d out -sourcepath $src $(find $src -name "*.java")
	else
	    echo "Variable 'javadocPerModule' in project.cfg is not true/false!"
	    if [[ $? -eq 0 ]]; then exit 1; fi
	fi
elif [[ $1 == "-p" ]]; then
    module=$2
	dir="$src/$module"
	if [[ -d $dir ]]; then
		#TODO >2 look >1
		echo "Generating javadoc only for $dir with default settings."
		files=$(find $src -name "javadoc" -prune -o -type d -print | sed "s/$src\///g; s/\//./g; s/$src//g" | grep "$module")
		if [[ $javadocPerModule == true ]]; then
		    javadoc -private -splitindex -use -author -version $files -sourcepath "./$src" -d "$src/$module/javadoc"
        elif [[ $javadocPerModule == false ]]; then
            if [[ -z ${jdout+null} ]]; then
                echo "Variable 'jdout' in project.cfg not set!"
                exit 1
            fi
		    javadoc -private -splitindex -use -author -version $files -sourcepath "./$src" -d "$jdout"
        fi
        if [[ $? -eq 0 ]]; then exit 1; fi
	else
		echo "Directory $dir not found."
		exit 1;
	fi
else
    echo "Wrong option. Use ./jd.sh [-p <moduleName>]"
    exit 1;
fi
