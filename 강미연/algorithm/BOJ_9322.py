for _ in range(int(input())):
    n = int(input())
    key1 = input().split()
    key2 = input().split()
    ciphertext = input().split()

    key1_map = {word: i for i, word in enumerate(key1)}
    rearrangement_map = [key1_map[word] for word in key2]

    plain_text_words = [0]*n
    for i in range(n):
        plain_text_words[rearrangement_map[i]] = ciphertext[i]

    print(" ".join(plain_text_words))
