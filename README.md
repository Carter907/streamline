### Burner: A Beginner-Friendly javac Wrapper


Burner is a beginner-friendly javac wrapper designed to make Java development more accessible by simplifying complex tasks.

As a newcomer to Java, I often struggled with tools like Maven and Gradle, finding them unnecessarily complicated for basic tasks like creating an executable JAR file. These tools, while powerful, can be intimidating for beginners due to their inherent complexity and the notorious "dependency hell" that often accompanies Java development.

With Burner, I aim to streamline the development experience and make it as seamless as possible. Inspired by build tools from other ecosystems, such as `cargo` for Rust and the `go` command for Golang, I believe Java deserves an equally beginner-friendly approach to tooling.

It’s important to note that the initial scope of this project is not to serve as a comprehensive build tool. Instead, burner is designed to be a user-friendly wrapper around javac, providing an approachable way for beginners to understand and appreciate the benefits of Java's existing tooling without being overwhelmed.

### Insallation
For now, the easiest way to run Burner is to create a jar file using the gradle task.
\
First, clone the repo.

```
git clone https://github.com/Carter907/burner.git
```
run the installation.
```
./gradlew install
```
Follow the instructions to run the the brn jar.

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
This is the most important file of any burner project. You can specify paths to module resolution as well
as the naming of each meaningful directory. You specify where you want your source code modules to be located (the default is `src`).

You also specify the name of the main class (`mainClass` property). This is helpful for bundling your module into a jar.

The default module name is `com.example` this should most likely be changed to whatever module name suits your needs.
The same can be said for the package `com.example`. `com.example` is a traditional default for package naming so it was used here
for conveniance.

### Usage

| command | purpose |
| ------- | ------- |
| **init**    | Initializes a new project |
| **build**   | Compiles the main module of your project |
| **run**     | Runs the compiled main module |
| **archive** | Packages your main module to a modular executable jar file |
| **clean**   | Removes the directory specified by `outDir` |

### branch.toml

This file specifies certain configuration for the javac command. Here is an example:
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
