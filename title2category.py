def title2category(map_filename, in_filename, out_filename):
    dict = {}
    with open(map_filename) as text:
        for line in text:
            word = line.split(",")
            dict[word[0]] = word[1].rstrip("\n")

    #print(dict)

    import operator

    with open(in_filename) as text:
        count = 0
        result = ""
        for line in text:
            title_dict = {}
            title = line.split("|")
            for word in title[1].split():
                if word in dict:
                    cat = dict[word]
                    if cat in title_dict:
                        title_dict[cat] += 1
                    else:
                        title_dict[cat] = 1

            if len(title_dict)==0:
                cat = 'Others'
            else:
                cat = max(title_dict.iteritems(), key=operator.itemgetter(1))[0]


            result += title[0] + '|' + cat + "|" + title[2]

            count = count + 1
            if(count%1000==0):
                writeFile(result, out_filename)
                result = ""
                print(count)

        if result!="":
            writeFile(result, out_filename)

def writeFile(result, out_filename):
    f = open(out_filename, 'a')
    f.write(result)
    f.close()

if ( __name__ == "__main__"):
    map_filename = "categorylookup.txt"
    in_filename = "dblp_master.txt"
    out_filename = "dblp_master_category.txt"
    title2category(map_filename, in_filename, out_filename)

