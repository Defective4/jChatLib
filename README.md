# jChatLib

jChatLib is a minimal Minecraft chat component **and** NBT parser library.  
It allows you to parse and encode NBT with both named and unnamed root tags, used in Minecraft's files and sent over network.
It also lets you parse text components, both from JSON and NBT!
The library is still under development, new features and improvement, as well as proper documentation are planned.

## Features
- **Complete** - supports all standard NBT data types
- **Flexible** - Allows parsing text components both from JSON and NBT, making it compatible with **all** Minecraft versions!
- **Simple to use**

## Installation
Add
```xml
<dependency>
    <groupId>io.github.defective4.minecraft</groupId>
    <artifactId>jchatlib</artifactId>
    <version>{version}</version>
</dependency>
```
to your `pom.xml`'s dependencies section, where `{version}` is:  
![Maven Central Version](https://img.shields.io/maven-central/v/io.github.defective4.minecraft/jchatlib)

## Usage

Parsing chat components from JSON:
```java
String json = "{\"text\": \"Hello \", \"extra\": [\"World\", {\"text\": \"!\"}]}";
JsonElement parsed = JsonParser.parseString(json);
ChatComponent component = ChatComponent.fromJson(parsed);
System.out.println(component.toPlainString()); // Outputs "Hello World!"
```

Parsing NBT from file:
```java
Tag nbt;
try(InputStream is = new FileInputStream("Hello_world.nbt")) {
    //                        v----- Read root tag name
    nbt = NBT.parse(is, true, false);
    //                              ^----- (don't) use GZIP compression 
} catch(Exception e) {
    e.printStackTrace();
    return;
}
System.out.println(nbt);
```

Parsing text components from NBT:
```java
InputStream in = ...
Tag tag = NBT.parse(in, false, false);
ChatComponent component = ChatComponent.fromNBT(tag);
System.out.println(component.toPlainString());
```

Creating a NBT file:
```java
try (OutputStream os = new FileOutputStream("test.nbt") {
    CompoundTag root = new CompoundTag("root");
    
    //                             v----- Tag name
    StringTag text = new StringTag("text", "Hello world!");
    //                                      ^----- Tag value

    root.add(text);        //      v--------- (don't) use network format
    byte[] data = NBT.encode(root, false);
    os.write(data);
}