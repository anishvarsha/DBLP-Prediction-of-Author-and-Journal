from sklearn.naive_bayes import GaussianNB
from sklearn.metrics import accuracy_score
import numpy as np

def dataset2Array(filename):
    features = []
    labels = []
    count = 0
    with open(filename) as text:
        for line in text:
            col = line.split('|')
            features.append( [col[0],col[2]] )
            labels.append(col[1])
            count = count + 1
    return features, labels

def classify(train_file):
    from datetime import datetime
    print(str(datetime.now()))
	
    features, labels = dataset2Array(train_file)
    from sklearn import cross_validation
    X_train, X_test, y_train, y_test = cross_validation.train_test_split(
        features, labels, test_size=0.2, random_state=42)

    # from sklearn.tree import DecisionTreeClassifier
    # clf = DecisionTreeClassifier()
    # clf.fit(X_train, y_train)
    # pred = clf.predict(X_test)

    from sklearn.ensemble import RandomForestClassifier
    clf = RandomForestClassifier(n_estimators=4)
    clf.fit(X_train, y_train)
    pred = clf.predict(X_test)

    from sklearn.metrics import accuracy_score
    print(accuracy_score(y_test, pred))
    from sklearn.metrics import precision_recall_fscore_support
    print(precision_recall_fscore_support(pred, y_test, average='micro'))

    print(str(datetime.now()))

if ( __name__ == "__main__"):
    train_file = "C:\Users\Suraj\Desktop\UTD\SEM 4\ML\Project\listVals.txt"
    classify(train_file)