# Prerequisite:
### You need to have Python2, Java, Apache Spark and SciKit-Learn installed.

**Step1**

Parse the xml file from http://dblp.uni-trier.de/xml/ using `xmlParser.R`. This will generate 3 files namely authorfinal.txt, journalfinal.txt and titlefinal.txt

**Step2**

Process the `titlefinal.txt` to count words and their frequencies using `wordextraction.py`. This will generate a file called `titlefreq.txt`

**Step3**

Remove stop words from `titlefreq.txt` and output the remaining words to `wordFreq.txt` using `TitleTokenizing.java`

**Step4**

Prepare a file called categorylookup.txt which is basically `titlefreq.txt` organized as (word, freq_count)

Run `title2category.py` to map the titles to respective category by taking in `dblp_master.txt` and `categorylookup.txt`, and outputing `dblp_master_category.txt`

**Step 5**

Convert the string file `dblp_master_category.txt` into a numeric one called `listVals.txt` using `dblp_master_category.py`

**Step 6**

Run the `classifier.py` on `listVals.txt`

**Step 7**

Run `GenerateProb.java` to convert `dblp_master_category.txt` into `author_category.txt`

Again, change the code to take a different key to `dblp_master_category.txt` into `author_journal.txt`

**Step 8**

Process `author_category.txt` using `ALS_category.scala` to generate recommendations for author-category combination.

Process `author_journal.txt` using `ALS_journal.scala` to generate recommendations for author-journal combination
