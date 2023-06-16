# file is executable
# write in terminal ./README.txt

# deleting target
rm -rf target

# make directory target
mkdir target

# compiling all sources files and move .class file to the target directory
javac src/java/edu/school21/printer/*/*.java -d target

# execute our program
java -classpath target edu/school21/printer/app/Program . 0 it.bmp