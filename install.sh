#!/usr/bin/env bash

outDir="build/libs"

gradle clean shadowjar

echo ""
echo "You can find the executable jar in $outDir."
echo "Move the jar file to wherever you are comfortable with."
echo "Create an alias for 'java -jar [path]/brancher.jar'."
echo ""
echo "Enjoy!"
