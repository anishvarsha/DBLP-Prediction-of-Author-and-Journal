import numpy as np
import csv

#file = open('dblp_master_category.txt', 'rU')
#data = file.read()

#for x in range(len(data)-1):
#	for y in range(3):
#		data[x][y] = float(data[x][y]);

#print data
dictionaryAuthor = {}
dictionaryCategory = {}
dictionaryJournal = {}
totalval = []
authorInc = 0
journalsInc = 0
categoryInc = 0

thefile = open('listVals.txt', 'w')
with open('dblp_master_category.txt', 'rb') as infh:
    reader = csv.reader(infh, delimiter='|')
    for row in reader:
    	if row[0] in dictionaryAuthor:
    		authorInc = authorInc+0;
    		authorIncTemp= dictionaryAuthor.get(row[0]);
    		row[0] = authorIncTemp
    	else:
    		authorInc = authorInc+1;
    		dictionaryAuthor.update({row[0]:authorInc})
    		row[0] = authorInc
    	if row[1] in dictionaryCategory:
    		categoryInc = categoryInc+0;
    		categoryIncTemp= dictionaryCategory.get(row[1]);
    		row[1] = categoryIncTemp;
    	else:
    		categoryInc = categoryInc+1;
    		dictionaryCategory.update({row[1]:categoryInc})
    		row[1] = categoryInc;

    	if row[2] in dictionaryJournal:
    		journalsInc = journalsInc+0;
    		journalsIncTemp = dictionaryJournal.get(row[2]);
    		row[2] = journalsIncTemp;
    	else:
    		journalsInc = journalsInc+1;
    		dictionaryJournal.update({row[2]:journalsInc})
    		row[2] = journalsInc;
    	strRow1 = str(row[0])
    	strRow2 = str(row[1])
    	strRow3 = str(row[2])
    	val = strRow1+'|'+strRow2+'|'+strRow3
    	totalval.append(val)
for item in totalval:
  thefile.write("%s\n" % item)