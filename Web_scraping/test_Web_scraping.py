"""
import csv
from operator import eq
from urllib.request import urlopen
from bs4 import BeautifulSoup

html = urlopen("http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001")
bsObj = BeautifulSoup(html, "html.parser")

table = bsObj.findAll("div", {"class":"list_body newsflash_body"})[0]
rows = table.findAll("li")

compareList = []

try:
    i = 0
    csvFile = open('news.csv', 'r')
    rdr = csv.reader(csvFile)

    for line in rdr:
        if i >= 20:
            break
        compareList.append(line[0])
        i += 1

    csvFile.close()
except:
    csvFile = open('news.csv', 'w')
    writer = csv.writer(csvFile)
    writer.writerow(('Title', 'Media', 'Date'))
    csvFile.close()


csvFile = open("news.csv", 'a')
writer = csv.writer(csvFile)


try:
    for temp in rows:
        row = temp.findAll(['dt', 'dd'])

        if len(row) == 3:
            title = row[1].get_text(" ", strip=True)
        else:
            title = row[0].get_text(" ", strip=True)

        media = temp.find( "span", {"class": "writing"} ).get_text()
        date = temp.find( "span", {"class": "date"} ).get_text()

        for item in compareList:
            if not eq(item, title):
                writer.writerow((title, media, date))
                break
finally:
    csvFile.close()
"""


# 스레드만 추가, 성능개선 필요
import threading
import copy
import csv
from urllib.request import urlopen
from bs4 import BeautifulSoup

compareList = []

class AsyncTask:
    def __init__(self):
        try:
            csvFile = open('news.csv', 'r', encoding='utf-8')
            csvFile.close()
        except:
            csvFile = open('news.csv', 'w', encoding='utf-8')
            writer = csv.writer(csvFile)
            writer.writerow(('Title', 'Media', 'Date'))
            csvFile.close()
        pass


    def TaskA(self):
        global compareList
        tempList = []

        html = urlopen("http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001")
        bsObj = BeautifulSoup(html, "html.parser")

        table = bsObj.findAll("div", {"class": "list_body newsflash_body"})[0]
        rows = table.findAll("li")

        csvFile = open("news.csv", 'a')
        writer = csv.writer(csvFile)

        try:
            for temp in rows:
                row = temp.findAll(['dt', 'dd'])

                if len(row) == 3:
                    title = row[1].get_text(" ", strip=True)
                else:
                    title = row[0].get_text(" ", strip=True)

                media = temp.find("span", {"class": "writing"}).get_text()
                date = temp.find("span", {"class": "date"}).get_text()

                tempList.append(title)

                if compareList.count(title) == 0:
                    writer.writerow((title, media, date))
            print("scraping")

            print("compareList")
            print(compareList)
            print("tempList")
            print(tempList)

            compareList.clear()
            compareList = copy.deepcopy(tempList)

        except:
            print("예외발생")
        finally:
            csvFile.close()

        threading.Timer(10,self.TaskA).start()


def main():
    at = AsyncTask()
    at.TaskA()

if __name__ == '__main__':
    main()

