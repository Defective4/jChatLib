# jChatLib

jChatLib is a minimal Minecraft chat componendt **and** NBT parser library.  

## Features
- **Complete** - supports all standard NBT data types
- **Flexible** - Allows parsing text components both from JSON and NBT, making it compatible with **all** Minecraft versions!
- **Simple to use**

## Installation
Soon

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
    nbt = NBTParser.parse(is, true, false);
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
Tag tag = NBTParser.parse(in, false, false);
ChatComponent component = ChatComponent.fromNBT(tag);
System.out.println(component.toPlainString());
```