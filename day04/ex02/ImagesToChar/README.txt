# file is executable
# write in terminal: */bash README.txt/*

# deleting target
rm -rf target

# make directory target
mkdir target
cp -rf ./src/resources ./target
cd target

# Unzip the libraries
jar xf ../lib/JColor-5.5.1.jar
jar xf ../lib/jcommander-1.78.jar
cd ..

# compiling all sources files and move .class file to the target directory
javac -classpath target src/java/edu/school21/printer/*/*.java -d target

# creating a .jar archive which will include all compiled .class files (manifest.txt contains the path to the Main class)
jar cfm ./target/images-to-chars-printer.jar src/manifest.txt -C ./target .

# execute our program
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN