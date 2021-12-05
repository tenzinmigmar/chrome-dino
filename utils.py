import pygame

class Dino(pygame.sprite.Sprite):
    def __init__(self, x, y):
        super(Dino, self).__init__()

        self.images = []
        self.images.append(pygame.image.load('Assets/Dino/DinoRun1.png'))
        self.images.append(pygame.image.load('Assets/Dino/DinoRun2.png'))
        self.index = 0

        self.image = self.images[self.index]

        self.rect = self.image.get_rect()
        self.rect.topleft = [x, y]

    def update(self):
        self.index += 1

        if self.index >= 2:
            self.index = 0

        self.image = self.images[self.index]

def clouds(img, screen, x, x2):
    screen.blit(img, (x, 90))
    screen.blit(img, (x2, 180))

def ground(img, screen, x, x2):
    ground = pygame.transform.scale(img, (1202, 14))
    screen.blit(ground, (x, 480))
    screen.blit(ground, (x2, 480))


