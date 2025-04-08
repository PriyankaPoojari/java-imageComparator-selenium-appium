# java-imageComparator-selenium-appium

# 📸 Image Comparator Utility for Java Projects

## 🔍 Use Case

When applications are opened across various mobile devices (e.g., iPhone, Samsung models), screen dimensions and rendering can differ significantly. This makes it challenging to visually compare how the UI looked in a previous release versus the current one.

**Image Comparator Utility** helps solve this problem by programmatically comparing screenshots and generating a new image that highlights the differences between two versions. This is especially useful for regression testing and ensuring consistent UI across devices.  
The utility code is easy to integrate with any Java based projects like selenium, appium

## ✅ Key Features

*   Compare two UI screenshots and highlight visual differences
*   Useful for responsive UI testing across different screen resolutions
*   Ideal for regression testing in mobile web or hybrid applications
*   Supports integration into CI/CD pipelines

## 🚀 How It Works

*   Run the utility to compare two images
*   A new image is generated with highlighted visual differences

I have explored 3 different libraries which can be used to compare images.

*   **Basic pixel to pixel comparison using Java inbuilt library**  
    Pro: No additional jar downloads or utility required  
    Con: Can only return true value if match or false if mismatch. Cannot highlight the difference.

```java
public static boolean compareImages(File fileA, File fileB) throws IOException {
    BufferedImage imgA = ImageIO.read(fileA);
    BufferedImage imgB = ImageIO.read(fileB);

    // Check dimensions first
    if (imgA.getWidth() != imgB.getWidth() || imgA.getHeight() != imgB.getHeight()) {
        return false;
    }

    for (int y = 0; y < imgA.getHeight(); y++) {
        for (int x = 0; x < imgA.getWidth(); x++) {
            if (imgA.getRGB(x, y) != imgB.getRGB(x, y)) {
                return false;
            }
        }
    }
    return true;
}
```

`For more usage details, refer to "src/comparison/BasicPixelToPixel.java"`

*   **Ashot library**  
    Pro: Can generate new image highlighting difference  
    Con: Not compatible with Selenium 4  
    For more usage details, refer to [https://github.com/pazone/ashot](https://github.com/pazone/ashot)

```
//Both not compatible with Selenium 4 dated 8th April 2025
        <!--<dependency>
            <groupId>io.github.bernardomg</groupId>
            <artifactId>ashot</artifactId>
            <version>1.7.0</version>  This version supports Selenium 4 
        </dependency>-->
        <!--<dependency>
            <groupId>ru.yandex.qatools.ashot</groupId>
            <artifactId>ashot</artifactId>
            <version>1.5.4</version>
        </dependency>-->
```

*   **Image-Comparison library**  
    Pro: Can generate new image highlighting difference  
    Con: Nothing as of. Compatible with Selenium 4  
    For more usage details, refer to [https://github.com/romankh3/image-comparison](https://github.com/romankh3/image-comparison)

```java
//compatible with Selenium 4 dated 8th April 2025
        <dependency>
            <groupId>com.github.romankh3</groupId>
            <artifactId>image-comparison</artifactId>
            <version>4.4.0</version>
        </dependency>
```

`For more usage details, refer to "src/comparison/ImageComparisonTest.java"`

## ⚙️ Pre-requisites

Make sure you have the following installed before using the utility:

*   Java (latest version. Works fine with v23)
*   Maven (to download dependency)

## 🛠️ Usage

\`\`\`  
Right click on ImageComparisonTest > run as TestNG class  
I have included multiple test scenarios to check behavior of this utility and to validate its output.

\`\`\`

## 🧪 Example Output of Image-Comparison library

![Comparing 2 different Pages](https://github.com/PriyankaPoojari/java-imageComparator-selenium-appium/blob/master/DifferentPage.png)

Diff image:

![](https://github.com/PriyankaPoojari/nodejs-ImageComparator/blob/master/diff-resemble.png)