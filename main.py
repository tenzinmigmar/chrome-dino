import pygame

pygame.init()

screen = pygame.display.set_mode((1000, 600))

# screen set-up
pygame.display.set_caption("Chrome Dino")
game_icon = pygame.image.load("assets/dino-icon.png")
pygame.display.set_icon(game_icon)

def main():
    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

if __name__ == "__main__":
    main()
