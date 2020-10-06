for x in *\ copy.jpg
do 

x2=${x::-9}

cp -v "$x" "$x2""b.png"
rm -v "$x"

done
