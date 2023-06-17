# file is executable
# write in terminal: */bash README.txt/*

# deleting target
rm -rf target

# make directory target
mkdir target

# compiling all sources files and move .class file to the target directory
javac src/java/edu/school21/printer/*/*.java -d target

cp -rf src/resources target

# creating a .jar archive which will include all compiled .class files (manifest.txt contains the path to the Main class)
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C target .

# execute our program
java -jar target/images-to-chars-printer.jar . 0