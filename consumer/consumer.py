#from cassandra.cluster import Cluster
#from cassandra.auth import PlainTextAuthProvider
from confluent_kafka import Consumer, KafkaError
import ast


#auth_provider = PlainTextAuthProvider(username='', password='')
#cluster = Cluster(["ip1","ip2"], auth_provider=auth_provider)
#session = cluster.connect('')

c = Consumer({
    'bootstrap.servers': '52.90.206.23:9092',
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
    dict_value = ast.literal_eval(str_value)
    print(dict_value) # Es igual que un json
    #insert_query =  "INSERT INTO pokedex (data1,data2,...) VALUES (%s, %s, %s)"%(data1,data2)
    #session.execute(insert_query)


c.close()
