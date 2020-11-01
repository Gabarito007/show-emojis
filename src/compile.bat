
javac br/com/hkp/showemojis/*.java
javac br/com/hkp/showemojis/apps/*.java
javac br/com/hkp/showemojis/global/*.java
javac br/com/hkp/showemojis/normalizefilenames/*.java
javac br/com/hkp/showemojis/gui/*.java


jar cfm Normalize.jar manifest1.txt br/com/hkp/showemojis/apps/*.class br/com/hkp/showemojis/global/*.class br/com/hkp/showemojis/normalizefilenames/*.class br/com/hkp/showemojis/gui/*.class

 
jar cfm ShowEmojis.jar manifest2.txt br/com/hkp/showemojis/apps/*.class br/com/hkp/showemojis/global/*.class br/com/hkp/showemojis/*.class br/com/hkp/showemojis/gui/*.class 


