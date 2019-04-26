import random

def random_size_restriction():
    size = random.randrange(100, 251)
    return "{}.{}".format(size // 100, size % 100)

def random_vehicle_restriction():
    should_have_restriction = random.random() < 0.35
    if should_have_restriction:
        return random.choice(['CAR', 'MOTORCYCLE', 'VAN'])
    else:
        return 'no restriction'

def random_space_string(floor_id, space_id):
    height, width = random_size_restriction(), random_size_restriction()
    restriction = random_vehicle_restriction()
    return "{},{},{},{},{}".format(floor_id, space_id, height, width, restriction)

def get_spaces():
    spaces = []

    for floor_num in range(0, 10):
        for space_num in range(0, 100):
            floor_id, space_id = floor_num, "{}{:02d}".format(floor_num, space_num)
            spaces.append(random_space_string(floor_id, space_id))
    
    random.shuffle(spaces)
    return spaces

def main():
    with open('spaces.csv', 'w') as outfile:
        outfile.write('Floor,Space,Width,Height,Restriction\n')
        for space in get_spaces():
            outfile.write(space + '\n')

if __name__ == '__main__':
    main()
