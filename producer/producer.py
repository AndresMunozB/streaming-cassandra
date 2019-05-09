import json
from confluent_kafka import Producer


p = Producer({'bootstrap.servers': '52.90.206.23:9092'})

def delivery_report(err, msg):
    if err is not None:
        print('Message delivery failed: {}'.format(err))
    else:
        print('Message delivered to {} [{}]'.format(msg.topic(), msg.partition()))


path_data_pokemon = "data/pokedex.json"
data = open(path_data_pokemon, 'r' )
data = json.load(data)

topic = "pokedex"

try:
    for pokemon in data:
        p.produce(topic, str(pokemon), callback=delivery_report)
        p.poll(2.0)

except KeyboardInterrupt:
    pass