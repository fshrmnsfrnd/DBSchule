import redis
from datetime import datetime
import time

# Teilaufgabe A
def get_next_session_id(redis_client):
    redis_client.incr('counter') 
    return redis_client.get("counter")

# Teilaufgabe B
def create_session(redis_client, user_id, username,email):
    id = str(get_next_session_id(redis_client))
    hashName = "session:" + id
    redis_client.hset(hashName, "user_id", str(user_id))
    redis_client.hset(hashName, "username", username)
    redis_client.hset(hashName, "email", email)
    redis_client.hset(hashName, "last_activity", datetime.now().isoformat())
    redis_client.hset(hashName, "created_at",  datetime.now().isoformat())
    redis_client.expire(hashName, 30*60)
    return id

# Teilaufgabe C
def update_activity(redis_client,session_id):
    redis_client.hset("session:"+session_id, "last_activity", datetime.now().isoformat())
    redis_client.expire("session:"+session_id, 30*60)

# Teilaufgabe D
def get_session(redis_client,session_id):
    if redis_client.exists("session:"+session_id):
        update_activity(redis_client, session_id)
        return dict(redis_client.hgetall("session:"+session_id))
    else:
        return None

# Teilaufgabe E
def delete_session(redis_client, session_id):
    if redis_client.exists("session:"+session_id):
        redis_client.delete("session:"+session_id)
        return 1
    else:
        return 0

# Teilaufgabe F
def get_all_active_sessions(redis_client):
    sessions = redis_client.keys("session:*")
    for session in sessions:
        redis_client.hgetall(session)
        redis_client.hset(session, "session_id", str(session)[8:-1])
    return sessions

# Teilaufgabe G
def allow_request(redis_client, session_id: int, max_requests: int, window_seconds: int) -> bool:
    key = f"session:{session_id}"
    if redis_client.hexists(key, "request_counter"):
        current = redis_client.hincrby(key, "request_counter", 1)
    else:
        redis_client.hsetex(key, "request_counter", "1", ex=window_seconds)
        current = 1
    if current > max_requests:
        return False
    return True

################## Testprogramm
# Verbindung zur Datenbank
print("Verbinde zur Datenbank")
redis_client = redis.Redis(host='localhost', port=6379, db=0, decode_responses=True)

redis_client.set('counter', 0) 

# Aufgabe 1 - A)
for i in range(0,3):
    new_id = get_next_session_id(redis_client)
    print('session_id',new_id)

# Aufgabe 1 - B)
# Session 2 erstellen
session_id1 = create_session(redis_client,
    user_id=123,
    username="max.mustermann",
    email="max@sa2.com"
)
print(f"Session erstellt: {session_id1}")

# Session 2 erstellen
session_id2 = create_session(redis_client,
    user_id=456,
    username="moritz.mustermann",
    email="max@sa2.com"
)
print(f"create_session: {session_id2}")

# Aufgabe 1 - C)
session_daten = get_session(redis_client,session_id1)
print("get_session:",session_daten)

# Aufgabe 1 - D)
update_activity(redis_client,session_id1)

# Aufgabe 1 - E)
result = delete_session(redis_client,session_id1)
print("delete_session:",result)

# Aufgabe 1 - F)
result = get_all_active_sessions(redis_client)
print("get_all_active_sessions:",result)

# Aufgabe 1 - G)
for i in range(11):
    allowed= allow_request(redis_client,session_id2,3,5)
    print(f"Versuch {i+1}: {'OK' if allowed else 'BLOCKED'}")
    time.sleep(1)

