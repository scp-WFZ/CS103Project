# CS103-Project

paper: https://cg.cs.tsinghua.edu.cn/traffic-sign/0682.pdf



## Dataset Annonation Transform

### [TT-100k](https://cg.cs.tsinghua.edu.cn/traffic-sign/):

#### The Map between category and traffic sign:

![image-20221117130842833](/Users/zhongfantasty/Library/Application Support/typora-user-images/image-20221117130842833.png)

#### The annonation format of JSON:

```json
{
  "imgs": {
    "id": {
      "path": "path/id.jpg",
      "objects": [
        {
          "category": "category_1",
          ...
        },
        ...
        {
          "category": "category_n",
          ...
        }
      ],
      "id": 1
    }
  }
}
```



### [Project Dataset](https://aistudio.baidu.com/aistudio/datasetdetail/107275/0)

#### The Map between category and traffic sign:

category from 0 to 57 ? 

unknow relation

#### The annonation format of CSV:

| file_name | width | height |  x1  |  y1  |  x2  |  y2  | category |
| :-------: | :---: | :----: | :--: | :--: | :--: | :--: | :------: |
|           |       |        |      |      |      |      |          |

