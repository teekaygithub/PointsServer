# Create the data sets
curl -X POST --data "{\"name\":\"DANNON\", \"points\":300, \"date\":\"2020-10-31T10:00:00\"}" -H 'Content-Type: application/json' http://localhost:8080/api/add
curl -X POST -d "{\"name\":\"UNILEVER\", \"points\":200, \"date\":\"2020-10-31T11:00:00\"}" -H 'Content-Type: application/json' http://localhost:8080/api/add
curl -X POST -d "{\"name\":\"DANNON\", \"points\":-200, \"date\":\"2020-10-31T15:00:00\"}" -H 'Content-Type: application/json' http://localhost:8080/api/add
curl -X POST -d "{\"name\":\"MILLER COORS\", \"points\":10000, \"date\":\"2020-11-01T14:00:00\"}" -H 'Content-Type: application/json' http://localhost:8080/api/add
curl -X POST -d "{\"name\":\"DANNON\", \"points\":1000, \"date\":\"2020-11-02T14:00:00\"}" -H 'Content-Type: application/json' http://localhost:8080/api/add

# Deduct 5000 points
curl -X POST -d "amount=5000" http://localhost:8080/api/spend