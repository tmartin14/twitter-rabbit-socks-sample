## Cloud Foundry Twitter Search

This is a distributed application containing 2 components:

- twitter2rabbit: A standalone Spring Integration application that periodically polls twitter for tweets containing the word "cloud" and places them in a Rabbit queue.
- rabbit2socks: A node.js application that consumes tweets from the Rabbit queue and pushes them to the browser using Sock.js

## Usage

### rabbit2socks
Prior to deploying the app, update the file public/index.html to the URL you plan to provide for the application:

```html
var sock = new SockJS("http://mytwittersearch.cloudfoundry.com/socks");
```

Package the application dependencies by running 'npm install'.  To deploy the application to Cloud Foundry, simply use the provided manifest.yml file.  You need only to provide the application with a name and URL (make sure you use the URL you specified in public/index.html):

```bash
cd rabbit2socks
npm install
vmc push
Would you like to deploy from the current directory? [Yn]:
Application Name: mytwittersearch
Application Deployed URL [mytwittersearch.cloudfoundry.com]:
```

Open a browser and navigate to http://mytwittersearch.cloudfoundry.com.  The page will be blank initially, but watch for dynamically added tweets once you've deployed twitter2rabbit.

### twitter2rabbit
Simply use the provided manifest.yml file to deploy the application to Cloud Foundry.  You need only to provide the application with a name.

```bash
cd twitter2rabbit
mvn package
vmc push
Would you like to deploy from the current directory? [Yn]:
Application Name: twitter2rabbit
```

Now just keep an eye on http://mytwittersearch.cloudfoundry.com and watch those new tweets pop up every 5 seconds!

#### Using Gradle
If you would prefer to use Gradle to build twitter2rabbit, simply run "gradle installApp". Then update the path and command in the manifest.yml file:

```bash
applications:
  build/install/twitter2rabbit:
...
command: bin/twitter2rabbit
```





