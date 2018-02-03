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
        except:                                                 # 파일이 없으면
            csvFile = open('news.csv', 'w', encoding='utf-8')
            writer = csv.writer(csvFile)
            writer.writerow(('Title', 'Media', 'Date'))
            csvFile.close()
        pass


    def TaskA(self):
        global compareList
        tempList = []
        setList = set([])

        html = urlopen("http://news.naver.com/main/list.nhn?mode=LSD&mid=sec&sid1=001")
        bsObj = BeautifulSoup(html, 'lxml', from_encoding='utf-8')

        table = bsObj.findAll("div", {"class": "list_body newsflash_body"})[0]
        rows = table.findAll("li")

        csvFile = open("news.csv", 'a')
        writer = csv.writer(csvFile)

        try:
            for temp in rows:
                row = temp.findAll(['dt', 'dd'])

                if len(row) == 3:
                    title = row[1].get_text(" ", strip=True)        # strip=True : 좌우 공백 제거
                else:
                    title = row[0].get_text(" ", strip=True)

                media = temp.find("span", {"class": "writing"}).get_text()
                date = temp.find("span", {"class": "date"}).get_text()

                tempList.append(title)

                if compareList.count(title) == 0:
                    #set의 특성을 사용하여 데이터 중복 제거 단 데이터의 순서는 지켜지지 않음
                    setList.add((title, media, date))

            resultList = list(setList)

            for item in resultList:
                writer.writerow(item)

            print("scraping")

            compareList.clear()                     # 다음 비교를 위해 리스트 초기화
            compareList = copy.deepcopy(tempList)   # 현재 시점의 데이터들을 다음 비교를 위해 복사

        except:
            print("예외발생")
        finally:                                # 예외발생 유무 상관 없이 실행
            csvFile.close()

        threading.Timer(5,self.TaskA).start()  # 5초 마다 TaskA 함수 실행


# 일정 시간마다 특정 함수 실행
def main():
    at = AsyncTask()
    at.TaskA()

if __name__ == '__main__':
    main()