import re
import string
ifile=open('titlefinal.txt','r')
input=ifile.read()
ifile.close()
formattedtext=re.sub('[^a-zA-Z \.]', ' ', input)
finput=formattedtext.replace("."," ")
wordlist=finput.lower().split(None)
wfreq={}
for word in wordlist:
	wfreq[word]=wfreq.get(word,0)+1
keylist=sorted(wfreq.keys())
for word in keylist:
   file2=open("titlefreq.txt",'a')
   file2.write(word+'*'+str(wfreq[word])+'\n')

