from cassandra.cluster import Cluster
from cassandra.auth import PlainTextAuthProvider
from confluent_kafka import Consumer, KafkaError
import ast
import random

auth_provider = PlainTextAuthProvider(username='cassandra', password='cassandra')
cluster = Cluster(["34.238.53.42"], auth_provider=auth_provider)
session = cluster.connect('pokemones')

print("ljñaskldk")


c = Consumer({
    'bootstrap.servers': '34.238.53.42:9092',
    'group.id': 'group',
    'auto.offset.reset': 'earliest'
})


c.subscribe(['pokedex'])

while True:
    msg = c.poll(0.5)
    if msg is None:
        continue
    if msg.error():
        print("Consumer error: {}".format(msg.error()))
        continue
    bin_value = msg.value()
    str_value = msg.value().decode('utf-8')
    p = ast.literal_eval(str_value)
    base = p['base']
    types = p['type']
    insert_query = ""
    longitude = random.uniform(-90,90)
    latitude = random.uniform(-90,90)
    if(len(types) == 2):
        insert_query = f"INSERT INTO pokemon (id, pokemonid, name, attack, defense, speed, hp, firsttype, secondtype, longitude, latitude) VALUES (now(), {p['id']}, \'{p['name']['english']}\', {base['Attack']}, {base['Defense']}, {base['Speed']}, {base['HP']}, \'{types[0]}\', \'{types[1]}\', {longitude}, {latitude})"
    else:
        insert_query = f"INSERT INTO pokemon (id, pokemonid, name, attack, defense, speed, hp, firsttype, longitude, latitude) VALUES (now(), {p['id']}, \'{p['name']['english']}\', {base['Attack']}, {base['Defense']}, {base['Speed']}, {base['HP']}, \'{types[0]}\', {longitude}, {latitude})"
    # print(p) # Es igual que un json
    # insert_query =  "INSERT INTO pokedex (data1,data2,...) VALUES (%s, %s, %s)"%(data1,data2)
    print(insert_query)
    session.execute(insert_query)


c.close()
