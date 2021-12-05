import pygame
from classes import Dino

pygame.init()

screen = pygame.display.set_mode((1100, 600))
FPS = 10
# screen set-up
pygame.display.set_caption("Chrome Dino")
game_icon = pygame.image.load("assets/Dino/dino-icon.png")
pygame.display.set_icon(game_icon)

def main():
    sprites = pygame.sprite.Group()
    dino = Dino(50, 400)
    sprites.add(dino)
    clock = pygame.time.Clock()

    running = True
    while running:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                running = False

        screen.fill((255, 255, 255))
        sprites.update()
        sprites.draw(screen)
        pygame.display.update()
        clock.tick(FPS)

if __name__ == "__main__":
    main()
