# Scan-To-Book-Transformer
In liberal arts classes in generally and philosophy in particular it has become common to distribute reading materials as pdf scans of a text. These scans vary drastically in quality. Often a large amount of space is wasted, the orientation is off, multiple pages of the book are on a single page of the scan and these problems are uniform across every page. This is a huge problem if you want to use these scans for many academic applications. Prints waste ink and are in smaller type then needed. E Readers are useless, and pen tablets are annoying to try and use. I was able to use existing tools for unpacking a pdf into an image gallery, copying files in pattern to match the number of pages per image, and adding images to to a document in order. The actual editing of those images into shape was however manual (there may be a photoshop trick to this but I don’t know it and couldn't find instructions). This project was a custom tool for that part of the workflow.
## Classes
This was a custom tool made in a hurry to solve a personal problem at the beginning of a semester. As such everything is implemented as a single class containing the main called “BookImageTransformer”. In the future I want to separate the image manipulation functions and the file manipulation functions into separate classes, or even packages, for better reuse.
## Methods
### filesList
Takes in a string treated as the name of a directory and returns an ArrayList of strings for every file name in that directory.
### cropImage
Takes in a BufferedImage object, and a float for the percent to crop from each side of that image. Returns the BufferedImage cropped by those percentages.
### staggerFileNames
Takes in the ArrayList from fileList and makes a copy of every image for scans where each image contains multiple pages.
### rotateImageByDegrees
Takes in a BufferedImage object, and a double to represent the number of degrees clockwise to rotate it. Returns the BufferedImage rotated by that amount.
### fullTransform
Takes in a string for a given image name, the floats for cropping it, and the double for rotating it. Then it performs the needed rotations and cropping. Finally it overwrites the results to the image file.
### run1profile
Takes in the ArrayList from fileList and, the needed transformation information, if there is one page to an image. It then runs fullTransform on every image in the list.
### run2profile
Takes in the ArrayList from fileList and, the needed transformation information, if there are two pages to an image. It then runs fullTransform on every image in the list, alternating profiles.
## Main
The main goes through the following steps
1. Initialize all the data
1. Take in the directory location
1. Take in the number of pages per image preferences
1. If needed run staggerFileNames
1. Take in profile 1 settings
1. Check if those settings are right
1. If twoProfile = true, do the previous 2 for profile 2
1. Execute run1profile or run2profile as appropriate. 
## Example
I will be using a file from my metaphysics class titled “From Everything Must Go” as an example as a page needs both croping and rortating. You can find it in "bookImageTransformer/FEMG". Here’s the first page prior to transformation.
![alt text](https://github.com/JeremyGDiamond/Scan-To-Book-Transformer/blob/master/FEMG/From%20Everything%20Must%20Go-01.jpg "unedited page 1")
With use of this tool every page is changed to this format
![alt text](https://github.com/JeremyGDiamond/Scan-To-Book-Transformer/blob/master/From%20Everything%20Must%20Go-01%20transformed.jpg "edited page 1")
### Note
This book is horrible. 
## Use 
1. Unpack a .pdf or .doc file into an image gallery with page numbers in the name and sort by name. Several tools exist for this.
1. Run the script pointed at the directory of that image gallery. No other files can be in this directory. This script is destructive so make sure to keep a copy of the original. The file system functions only sort correctly on windows.
1. Pull the image gallery into your word processor of choice and convert that document as needed.
