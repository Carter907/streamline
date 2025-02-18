### burn: A Beginner-Friendly javac Wrapper


Burn is a beginner-friendly javac wrapper designed to make Java development more accessible by simplifying complex tasks.

As a newcomer to Java, I often struggled with tools like Maven and Gradle, finding them unnecessarily complicated for basic tasks like creating an executable JAR file. These tools, while powerful, can be intimidating for beginners due to their inherent complexity and the notorious "dependency hell" that often accompanies Java development.

With burn, I aim to streamline the development experience and make it as seamless as possible. Inspired by build tools from other ecosystems, such as `cargo` for Rust and the `go` command for Golang, I believe Java deserves an equally beginner-friendly approach to tooling.

It’s important to note that the initial scope of this project is not to serve as a comprehensive build tool. Instead, burn is designed to be a user-friendly wrapper around javac, providing an approachable way for beginners to understand and appreciate the benefits of Java's existing tooling without being overwhelmed.

### Insallation
For now, the easiest way to run burn is to create a jar file using the `install.sh` script.
\
First, clone the repo.

```
git clone https://github.com/Carter907/burn.git
```
run the installation.
```
./install.sh
```
Follow the instructions and have fun.


### Usage

```
burn run
```
runs a project
```
burn build
```
builds a project
```
burn archive
```
packages a project to an executable jar file

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
