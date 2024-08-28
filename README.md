# 🔍 Levenshtein Distance Java Library
![Maven Central Version](https://img.shields.io/maven-central/v/xyz.nextn/simple-levenshtein-distance)
[![codecov](https://codecov.io/github/nextn-xyz/simple-levenshtein-distance/branch/main/graph/badge.svg?token=H707CXAZP3)](https://codecov.io/github/nextn-xyz/simple-levenshtein-distance)

A simple and efficient Java library for calculating the Levenshtein Distance, the most popular algorithm for measuring the difference between two strings. Ideal for applications requiring fuzzy string matching, spell checking, or natural language processing.

## 🚀 Features
- **Fast & Lightweight:** Designed to be efficient with minimal dependencies.
- **Easy to Use:** Simple API for quick integration.
- **Highly Accurate:** Implements the standard Levenshtein distance algorithm.
- **Open Source:** MIT licensed, so you can freely use and modify it.

## 📦 Installation
### Maven
Add the following dependency to your `pom.xml`:
```xml
<dependency>
    <groupId>xyz.nextn</groupId>
    <artifactId>simple-levenshtein-distance</artifactId>
</dependency>
```

## 🛠️ Usage
Here's how you can calculate the Levenshtein distance between two strings:
```java
package org.example;

import xyz.nextn.levenshteindistance.LevenshteinDistance;

public class Main {
    public static void main(String[] args) {
        String h1 = "Hello, World!";
        String h2 = "Hello, World??";
        System.out.println(LevenshteinDistance.calculate(h1, h2));
    }
}
```
### Example Scenarios
- Spell Checking: Compare a misspelled word with a dictionary.
- Plagiarism Detection: Measure similarity between documents.
- Bioinformatics: Compare genetic sequences.

## 🧪 Testing
We use JUnit for testing. To run the tests:
```shell
mvn test
```

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🌟 Support
If you find this project helpful, please give it a ⭐ on GitHub!
If you have any questions or suggestions, feel free to open an issue or submit a pull request.

