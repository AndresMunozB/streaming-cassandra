import json
import random
from confluent_kafka import Producer


p = Producer({'bootstrap.servers': '34.238.53.42:9092'})

def delivery_report(err, msg):
    if err is not None:
        print('Message delivery failed: {}'.format(err))
    else:
        print('Message delivered to {} [{}]'.format(msg.topic(), msg.partition()))


path_data_pokemon = "data/pokedex.json"
data_s = open(path_data_pokemon, 'r' )
data_j = json.load(data_s)
data_s.close()

topic = "pokedex"

try:
	while True:
		index = random.randint(0,len(data_j)-1)
		pokemon = data_j[index]
		print(index)
		print(pokemon)
		p.produce(topic, str(pokemon), callback=delivery_report)
		p.poll(2.0)

except KeyboardInterrupt:
    pass