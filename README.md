# AllureReporting

1. This is maven project. See pom.xml file to check all dependencies

2. For Video recoding I used MyScreenRecorder.java

3. For Allure report I used AllureListener.java

4. Allure requires Java 8 or higher

5. For Allure commonds need to install tool: -> npm install -g allure-commandline --save-dev

6. To open report : -> /usr/local/bin/allure open Report/

7. To publish report use : https://app.netlify.com/sites/focused-liskov-00f75d/deploys

8. Drop Report folder in it.


# Tesseract (Get Text from image)

1. Install tesseract (default eng language):
	  brew install tesseract 

2. For other language : 
	  brew install tesseract-lang

3. Run this command:
	  brew link --overwrite tesseract

4. Go to tesseract location:
	  cd /usr/local/Cellar/tesseract/4.1.1/include

5. Get Text from give image : 
	  tesseract <location of image file> <location, where we want to save text> -l eng

Exp:
	LTB0206984-Mac:include b0097042$ tesseract /Users/b0097042/Downloads/Hackathon.jpeg /Users/b0097042/Downloads/textabc-eng -l eng

