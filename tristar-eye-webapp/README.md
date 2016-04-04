# tristar-eye
The mobile app that helps to check current Tricity traffic status.

## Configuration:

### Basic dependencies:
- Maven ([http://maven.apache.org/](http://maven.apache.org/))
- Node ([http://nodejs.org/](http://nodejs.org/)) -`npm install`
- Bower ([http://bower.io/](http://bower.io/)) -`npm install -g bower`

### Resolve JS/CSS dependencies:
Please resolve bower dependencies by running the command...
```
bower install
```
...from /src/main/webapp/ location.

### IDE Plugins:
For Eclipse GWT plugin http://gwt-plugins.github.io/documentation/gwt-eclipse-plugin/Download.html and M2E http://www.eclipse.org/m2e/ .
For IntelliJ IDEA I use bundled plugins. Unfortunately Errai IntelliJ Plugin fails a bit so I don't use it.

Your IDE will also need GWT SDK (http://www.gwtproject.org/download.html). Please download and point to the proper path in your project configuration.

### Run
Run it using Maven. You can run it with your IDE or simply use console:
```
mvn clean gwt:run
```

For this step, you need to have Maven installed first (https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html).
