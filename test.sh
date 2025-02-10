#! /usr/bin/env bash

OUT="build/libs/brancher.jar"
TEST_DIR="src/test/example_project"

gradle shadowjar

mv "$OUT" "$TEST_DIR"

cd $TEST_DIR || exit

echo "testing run..."

java -jar brancher.jar run

echo "testing build..."

java -jar brancher.jar build

echo "testing archive..."

java -jar brancher.jar archive

echo "cleaning..."

rm -rf build
echo "finished."
