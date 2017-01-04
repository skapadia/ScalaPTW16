- Install Java 1.8 JDK:
  - You can find instructions for installing the JDK for different platforms here: http://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html
  - OR you can directly download the JDK from here: http://www.oracle.com/technetwork/java/javase/downloads/index.html
    - Click on the Downloads tab:  As of this writing, the latest version is Java SE 8u77 - click the Download button under the label JDK
    - Choose the executable appropriate for your platform
    - Download and install, leaving all of the default settings during installation
    - For example, on the Mac, the default location (“home” directory) will be: On the Mac, by default this will be /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home

- Install Intellij
  - Download and install the Community edition (Mac or Windows or Linux):  https://www.jetbrains.com/idea/download/
  - Choose your platform, OS X, Windows, or Linux
  - Click the Download button under the Community edition
  - After the download completes, run the executable and accept the defaults

- Installing the Intellij Scala Plugin
  - After installation completes, run Intellij.
  - IntelliJ will start up with a dialog with options such as “Create New Project”, “Import Project”, etc.  
  - At the bottom right, there is a Configure drop-down:  Click the Configure drop-down and select Plugins
  - Click the “Install JetBrains plugin…” button at the bottom left
  - In the search field at the top, type “Scala"
  - The Scala 3.0.1 (or newer) plugin should appear, with details in the right pane
  - Click the green Install button
  - This will download and automatically install the Scala plugin
  - After that completes, click Close
  - Intellij will ask if you want to restart it to activate plugin changes - click “Restart"

- Creating a Scala project
  - After it restarts, click “Create New Project” - Scala should show up as an option on the left-hand side. 
  - Click Scala
  - Create a sample project called “ScalaSample"
  - Click “Create” next to Scala SDK and then click "Download"
  - This will download the latest Scala (2.11.8 as of this writing) and SBT (0.13.8 as of this writing) - note this may take some time
  - After it completes, make sure “scala-sdk-2.11.8” shows up in the Scala SDK drop down
  - For Project SDK, you need to choose the location where the Java 1.8 SDK was installed
    - On the Mac, by default this will be /Library/Java/JavaVirtualMachines/jdk1.8.0_77.jdk/Contents/Home
  - Then click “Finish"

You have successfully installed Java, Intellij, and Scala within the IDE
