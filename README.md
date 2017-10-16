###A collection of programs and help bash scripts for Java course at University of Wrocław.
######author: Tooster
##Compiling and executing
To compile and run a program:
1. If you can't run 'compile.sh' and 'run.sh' make them executable:
    * `chmod +x compile.sh run.sh`
2. Use compile.sh script to compile a project:
    * `./compile` to build the newest version.
    * `.compile <name>` to build 'src/\<name>' directory
3. Use run.sh to execute a project:
    * `./run <arguments...>` will run the newest project defined in 'project.cfg'
        with <arguments> as line arguments.
    * `./run -p <name> <arguments>` will run the \<name> project with <arguments>
        as line arguments.
        
##Important
project.cfg contains important variables for scripts, such as names of classes with 
main functions for each package etc. Change only if you know what are you doing.