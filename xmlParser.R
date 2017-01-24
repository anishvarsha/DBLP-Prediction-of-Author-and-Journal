library('XML')
library('UnidecodeR')
setwd('Desktop/')

xmlParsedDoc <-  xmlRoot(xmlTreeParse('dblp.xml',getDTD = TRUE ,options=XML::DTDLOAD, useInternalNodes = TRUE))

namespaceDTD <- c(soap="http://schemas.xmlsoap.org/soap/envelope/",
        xsd="http://www.w3.org/2001/XMLSchema",
        xsi="http://www.w3.org/2001/XMLSchema-instance",
        sr="http://www.cuahsi.org/waterML/1.0/",
        gsr="http://www.cuahsi.org/his/1.0/ws/")


author = xpathSApply(xmlParsedDoc, "//article/author", xmlValue, namespaces = namespaceDTD)
journal = xpathSApply(xmlParsedDoc, "//article/journal", xmlValue, namespaces = namespaceDTD)
title = xpathSApply(xmlParsedDoc, "//article/title", xmlValue, namespaces = namespaceDTD)

tuples = list(author, journal, title);

write.csv(author, file = "authorfinal.txt", row.names = FALSE)
write.csv(journal, file = "journalfinal.txt",row.names = FALSE)
write.csv(title, file = "titlefinal.txt", row.names = FALSE)
