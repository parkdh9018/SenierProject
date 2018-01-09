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
    readFile = open('news.csv', 'r')
    rdr = csv.reader(readFile)

    for line in rdr:
        if i >= 20:
            break
        compareList.append(line[0])
        i += 1

    readFile.close()
except:
    print("파일 없음")


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

