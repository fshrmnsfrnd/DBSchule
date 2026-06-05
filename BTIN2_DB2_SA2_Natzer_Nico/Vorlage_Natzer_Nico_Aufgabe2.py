from pymongo import MongoClient, ASCENDING, DESCENDING, TEXT
from datetime import datetime
from bson.objectid import ObjectId
from typing import List, Dict, Optional

# Teilaufgabe A
def create_post(posts, title: str, content: str, author: str, taglist:list = None) -> str:
    if taglist == None:
        taglist = []
    created = posts.insert_one({
        "title": title,
        "content": content,
        "author": author,
        "tags": taglist,
        "updated_at": datetime.now(),
        "comments": [],
        "likes": 0,
    })
    return created.inserted_id

# Teilaufgabe B
def get_post(posts,post_id: str):
    return posts.find_one({"_id": ObjectId(post_id)})

# Teilaufgabe C
def update_post(posts, post_id: str, title:str = None, content: str = None, taglist: str = None) -> bool:
    if title != None:
        posts.update_one({"_id": ObjectId(post_id)}, {"$set": {"title": title}})
    if content != None:
        posts.update_one({"_id": ObjectId(post_id)}, {"$set": {"content": content}})
    if taglist != None:
        posts.update_one({"_id": ObjectId(post_id)}, {"$set": {"tags": taglist}})
    
    if title != None or content != None or taglist != None:
        return True
    return False

# Teilaufgabe D
def delete_post(posts, post_id: str) -> bool:
    deleted = posts.delete_one({"_id": ObjectId(post_id)})
    if deleted == 1:
        return True
    return False

# Teilaufgabe E
def create_indexes(posts):
    posts.create_index({"tags": ASCENDING})
    posts.create_index({"created_at": DESCENDING})
    posts.create_index({"title": TEXT, "content": TEXT})

# Teilaufgabe F
def get_posts_by_tag(posts, tag: str) -> Dict:
    result = posts.find({"tags": tag}).sort({"updated_at": DESCENDING})
    liste = list(result)
    dic = {}
    postlist = []
    total = 0
    for doc in result:
        postlist.append(doc)
        total = total + 1
    dic.update({"posts": postlist})
    dic.update({"total": total})
    return dic

# Teilaufgabe G
def like_post(posts, post_id: str) -> bool:
    result = posts.update_one(
	    {"_id": ObjectId(post_id)}, 
	    {"$inc": {"likes": 1}} 
    )
    if result.modified_count == 1:
        return True
    return False


# Teilaufgabe H
def delete_tag(posts, post_id: str, tag: str) -> bool:
    tags = list(get_post(posts, post_id)["tags"])
    tags.remove(tag)
    updated = posts.update_one({"_id": ObjectId(post_id)}, {"$set": {"tags": tags}})
    if updated == 1:
        return True
    return False


# Testprogramm
# Verbindung zur Datenbank
print("Verbinde und erstelle Datenbank und Collection posts")
client = MongoClient("mongodb://localhost:27017/")
db = client['blog_db']
posts = db['posts']

# Aufgabe 3 - A)
post1_id = create_post(posts,
        title="Einführung in MongoDB",
        content="MongoDB ist eine NoSQL-Datenbank...",
        author="Max Mustermann",
        taglist=["mongodb", "database", "nosql","SA2"])
print(f" Post1 erstellt: {post1_id}")
    
post2_id = create_post(posts,
        title="Python Best Practices",
        content="In diesem Artikel schauen wir uns Python Best Practices an...",
        author="Anna Schmidt",
        taglist=["python", "programming", "best-practices","SA2"])
print(f" Post2 erstellt: {post2_id}")
    
post3_id = create_post(posts,
        title="MongoDB Aggregation Pipeline",
        content="Die Aggregation Pipeline ist ein mächtiges Tool...",
        author="Max Mustermann",
        taglist=["mongodb", "aggregation", "database","SA2"])
print(f" Post3 erstellt: {post3_id}\n")

# Aufgabe 3 - B)
post = get_post(posts,post3_id)
print('get_post',post)

# Aufgabe 3 - C)
result = update_post(posts,post3_id,'titel-changed','content-changed')
print('update_post:',result)

# Aufgabe 3 - D)
result = delete_post(posts,post3_id)
print('delete_post (post3_id):',result)

# Aufgabe 3 - E)
create_indexes(posts)
print('create_indexes: aufgerufen')

# Aufgabe 3 - F)
results = get_posts_by_tag(posts,"SA2")
print('get_posts_by_tag (SA2):',results)

# Aufgabe 3 - G)
for i in range(0,4):
    like_post(posts,post2_id)

result = get_post(posts,post2_id)
print(result)

# Aufgabe 3 - H)
if delete_tag(posts,post2_id,"SA2"):
    result = get_post(posts,post2_id)
    print(result)

