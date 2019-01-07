# Scan-To-Book-Transformer
In the world of liberal arts classes in general and philosophy in particular it has become common to distribute reading materials as pdf scans of a text. These scans vary drastically in quality. Often a large amount of space is wasted, the orientation is off, multiple pages of the book are on a single page of the scan and these problems are uniform across every page. This is a huge problem if you want to use these scans in a for many academic applications. Prints waste ink and are in smaller type then needed. E Readers are useless, and pen tablets are annoying to try and use. Tolls exist for unpacking a pdf into an image gallery, copying files in pattern to match the number of pages per image, and adding images to to a document in order. The actual editing of those images into the shape was however manual (there may be a photoshop trick to this but I don’t know it and couldn't find instructions). This project was a custom tool for that part of the workflow. I will be using a file from my metaphysics class titled “From Everything Must Go” as an example. Here’s the first page prior to transformation.
![alt text](https://github.com/JeremyGDiamond/Scan-To-Book-Transformer/blob/master/bookImageTransformer/FEMG/From%20Everything%20Must%20Go-01.jpg "unedited page 1")
## Classes
This was a custom tool made in a hurry to solve a personal problem at the beginning of a semester. As such everything is implemented as a single class containing the main called “BookImageTransformer”. In the future I want to separate the image manipulation functions and the file manipulation functions into separate classes, or even packages, for better reuse.

## Functions
### filesList

### cropImage

### staggerFileNames

### rotateImageByDegrees

### fullTransform

### run1profile

### run2profile

## Main
