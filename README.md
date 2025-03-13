### Streamline: A Simple, Beginner-Friendly Java Build Tool
![License](https://img.shields.io/badge/license-GPL3-blue.svg) <!-- Replace with your license -->


Streamline is a beginner-friendly build tool designed to make Java development more accessible by creating a simple abstraction above the [Jdk binary tool suite](https://docs.oracle.com/en/java/javase/23/docs/specs/man/index.html).

As a newcomer to Java, I often struggled with tools like Maven and Gradle, finding them unnecessarily complicated for basic tasks. These tools, while powerful, can be intimidating for beginners due to their inherent complexity. Modern Java build tools were made for enterprise development, and it's time the Java community saw something different. 

With Streamline, I aim to strengthen the development experience and make it as simple to use and learn from as possible. Streamline takes its ease of use from build tools of other languages, such as `cargo` for Rust and the `go` command for Golang.

This project intends to follow the principles of transparency, simplicity, and ease of use. Our mission is to create a build tool that can be used in an academic or hobby environment to facilitate learning about simple project management in Java.

It’s important to note that the initial scope of this project is not to serve as a comprehensive build tool. Dependencies will still have to be manually managed. However, Streamline is unopinionated, and because of its transparency, we implore developers to use other open-source tools alongside your project, such as Make, to help facilitate your build environment.

### Key Considerations
- Currently, Streamline does not automatically download and resolve dependencies like traditional build tools.
   - Dependencies are placed into a special folder and added to the module path when you want to run or build the project.
- The Java Module Platform System is proritized.
   - Modules are a great way to structure your Java project and this project works with them from the ground up.

### Installation
For now, the easiest way to run Streamline is to create a jar file using the gradle task.
\
First, clone the repo.

```
git clone https://github.com/Carter907/streamline.git
```
run the installation.
```
./gradlew install
```
Follow the instructions to run the the `stml` jar.

### Project Structure
When you use the `init` command, you create a project with some helpful defaults. This includes the following project structure:
```
├── branch.toml
├── libs
└── src
   └── com.example
      ├── com
      │  └── example
      │     └── Main.java
      └── module-info.java
```
Lets go through each of these paths:

`branch.toml` this is your main project file that specifies all the configuration for your project.
This is the most important file of any sreamline project. You can specify paths to module resolution as well
as the naming of each meaningful directory. You specify where you want your source code modules to be located (the default is `src`).

You also specify the name of the main class (`mainClass` property). This is helpful for bundling your module into a jar.

The default module name is `com.example` this should most likely be changed to whatever module name suits your needs.
The same can be said for the package `com.example`. `com.example` is a traditional default for package naming so it was used here
for convenience.

### Usage

| command | purpose |
| ------- | ------- |
| **init**    | Initializes a new project |
| **build**   | Compiles the main module of your project |
| **run**     | Runs the compiled main module |
| **archive** | Packages your main module to a modular executable jar file |
| **clean**   | Removes the directory specified by `outDir` |

### branch.toml

This file specifies certain configuration for your project. Here is an example:
```toml
[project]
name = "My Project"
mainClass = "Main"

[build]
sourceDir = "src"
outDir = "build"

[archive]
jarName = "app"

[modules]
mainModule="client"
```
As you can tell, simplicity is the primary goal for this project. It may seem unnatural at first, but it will be more powerful as other features are added.
