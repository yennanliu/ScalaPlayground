import requests
import json

# https://www.contentful.com/blog/2021/01/14/GraphQL-via-HTTP-in-five-ways/

spaceID = "mt0pmhki5db7"
accessToken = "8c7dbd270cb98e83f9d8d57fb8a2ab7bac9d7501905fb013c69995ebf1b2a719"

endpoint = f"https://graphql.contentful.com/content/v1/spaces/{spaceID}"
headers = {"Authorization": f"Bearer {accessToken}"}

query = """query {
  showCollection{
    items {
      title
      firstEpisodeDate
      lastEpisodeDate
      henshinMp4 {
        url
      }
    }
  }
}"""

r = requests.post(endpoint, json={"query": query}, headers=headers)
if r.status_code == 200:
    print(json.dumps(r.json(), indent=2))
else:
    raise Exception(f"Query failed to run with a {r.status_code}.")