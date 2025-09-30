#!/bin/bash
set -e

echo ">>> Updating system..."
sudo apt update
sudo apt upgrade -y

echo ">>> Installing Java 17 JDK..."
sudo apt install -y openjdk-17-jdk

echo ">>> Installing Maven (optional, for builds)..."
sudo apt install -y maven

echo ">>> Installing utilities..."
sudo apt install -y unzip curl wget git

echo ">>> Verifying versions..."
java -version
mvn -version || echo "Maven not found (skipped?)"

echo ">>> Setup complete!"
