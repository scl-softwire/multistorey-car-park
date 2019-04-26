import random
from string import ascii_uppercase

active_reg_numbers = []

def random_size_restriction():
    size = random.randrange(100, 251)
    return "{}.{}".format(size // 100, size % 100)

def random_letter():
    return random.choice(ascii_uppercase)

def random_digit():
    return random.randrange(0, 10)

def random_vehicle_type():
    return random.choice(['CAR', 'VAN', 'MOTORCYCLE'])

def random_reg_number():
    return "{}{}{}{}{}{}{}".format(random_letter(), random_letter(), random_digit(), random_digit(), random_letter(), random_letter(), random_letter())

def generate_entry(reg_number):
    return "Entry,{},{},{},{}".format(random_vehicle_type(), reg_number, random_size_restriction(), random_size_restriction())

def generate_exit(reg_number):
    return "Exit,{}".format(reg_number)

def generate_event():
    is_entry = (random.random() > 0.3 or len(active_reg_numbers) == 0)

    if is_entry:
        reg_number = random_reg_number()
        active_reg_numbers.append(reg_number)
        return generate_entry(reg_number)
    else:
        reg_number = random.choice(active_reg_numbers)
        active_reg_numbers.remove(reg_number)
        return generate_exit(reg_number)

def main():
    with open('events.txt', 'w') as outfile:
        for _ in range(100000):
            outfile.write(generate_event() + '\n')

if __name__ == '__main__':
    main()
