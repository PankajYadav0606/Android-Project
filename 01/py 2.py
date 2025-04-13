def main():
    wall_length_km = 10  # Length of the wall in kilometers
    speed_kmh = 1       # Speed of the person in kilometers per hour

    time_hours = wall_length_km / speed_kmh  # Calculate time in hours

    print(f"The person will take {time_hours:.2f} hours to run a {wall_length_km} km wall.")

if __name__ == "__main__":
    main()
